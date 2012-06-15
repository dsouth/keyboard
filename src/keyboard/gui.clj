(ns keyboard.gui
  (:import [java.awt Color Dimension]
           [javax.swing JComponent]))

(def key-width 25)
(def key-height 125)
(def component-width (inc (* 8 key-width)))
(def component-height (inc key-height))

(defn keyboard-paint [g]
  (.setColor g Color/WHITE)
  (.fillRect g 0 0 component-width component-height)
  (.setColor g Color/BLACK)
  (doseq [x (range 8)]
    (.drawRect g (* x key-width) 0 key-width key-height)))

(def keyboard-component
  (proxy [JComponent] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (keyboard-paint g))))

(defn get-keyboard-component []
  (.setPreferredSize keyboard-component (Dimension. component-width component-height))
  (.setBackground keyboard-component Color/WHITE)
  keyboard-component)
