(ns keyboard.core
  (:use [overtone.live]
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

(defn key-pressed [e]
  (let [k (.getKeyCode e)]
    (println "keycode is" k)
    (if-let [mapped (key-code->base-note k)]
      (let [note (+ root-note mapped)]
        (println "Note on" note)
        (mooger :note note)))))

(defn key-released [e]
  (println "Note off")
  (ctl mooger :gate 0))

(defn key-typed [e])

(defn synth-frame []
  (let [frame (JFrame.)
        comp (proxy [JComponent] [])]
    (.addKeyListener comp
                     (proxy [KeyListener] []
                       (keyTyped [e] (key-typed e))
                       (keyPressed [e] (key-pressed e))
                       (keyReleased [e] (key-released e))))
    (.add frame comp)
    (.pack frame)
    (.requestFocus comp)
    (.setVisible frame true)))
