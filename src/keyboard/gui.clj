(ns keyboard.gui
  (:import [java.awt Dimension]
           [javax.swing JComponent]))

(def key-width 25)
(def key-height 200)

(defn keyboard-paint [g])

(def keyboard-component
  (proxy [JComponent] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (keyboard-paint g))))

(defn get-keyboard-component []
  (.setPreferredSize keyboard-component (Dimension. (* 8 key-width) key-height))
  keyboard-component)
