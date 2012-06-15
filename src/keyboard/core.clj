(ns keyboard.core
  (:use [keyboard.gui]
        [overtone.live]
        [overtone.inst.synth])
  (:import [java.awt.event KeyListener]
           [javax.swing JComponent JFrame]))

(def root-note 64)

(def key-code->base-note
  {65 0
   87 1
   83 2
   69 3
   68 4
   70 5
   84 6
   71 7
   89 8
   72 9
   85 10
   74 11
   75 12})

(def key-code->note-off (ref {}))

(defn key-pressed [e]
  (let [k (.getKeyCode e)]
    (println "keycode is" k)
    (if-let [mapped (key-code->base-note k)]
      (let [note (+ root-note mapped)
            node (:id (mooger :note note))]
        (dosync
         (alter key-code->note-off assoc k node))))))

(defn key-released [e]
  (let [k (.getKeyCode e)]
    (if-let [node (@key-code->note-off k)]
      (ctl node :gate 0)
      (dosync
       (alter key-code->note-off dissoc k)))))

(defn key-typed [e])

(defn synth-frame []
  (let [frame (JFrame.)
        comp (get-keyboard-component)]
    (.addKeyListener comp
                     (proxy [KeyListener] []
                       (keyTyped [e] (key-typed e))
                       (keyPressed [e] (key-pressed e))
                       (keyReleased [e] (key-released e))))
    (-> (.getContentPane frame)
        (.add comp))
    (.pack frame)
    (.requestFocus comp)
    (.setVisible frame true)))
