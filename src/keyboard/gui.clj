(ns keyboard.gui
  (:import [java.awt Color Dimension]
           [javax.swing JComponent]))

(def key-width 25)
(def key-height 125)

(defn keyboard-paint [g]
  (println "DRAWING :P")
  (.setColor g Color/BLACK)
  (doseq [x (range 8)]
    (.drawRect g (* x key-width) 0 key-width key-height)))

(def keyboard-component
  (proxy [JComponent] []
    (paintComponent [g]
      (println "paint component")
      (proxy-super paintComponent g)
      (keyboard-paint g))))

(defn get-keyboard-component []
  (.setPreferredSize keyboard-component (Dimension. (inc (* 8 key-width)) (inc key-height)))
  (.setBackground keyboard-component Color/WHITE)
  keyboard-component)
