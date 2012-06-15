(ns keyboard.core
  (:use [overtone.live]
        [overtone.inst.synth])
  (:import [java.awt.event KeyListener]
           [javax.swing JComponent JFrame]))

(defn key-pressed [e]
  (let [k (.getKeyCode e)]
    (println "Note on" k)
    (mooger :note k)))

(defn key-released [e]
  (println "Note off")
  (ctl mooger :gate 0))

(defn key-typed [e])

(defn snyth-frame []
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
