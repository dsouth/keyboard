(ns keyboard.gui
  (:import [java.awt Color Dimension]
           [javax.swing JComponent]))

(def key-width 24)
(def key-height 110)
(def blacks [0 1 3 4 5])
(def component-width (inc (* 8 key-width)))
(def component-height (inc key-height))

(defn keyboard-paint [g]
  (.setColor g Color/WHITE)
  (.fillRect g 0 0 component-width component-height)
  (.setColor g Color/BLACK)
  (doseq [x (range 8)]
    (.drawRect g (* x key-width) 0 key-width key-height))
  (doseq [b blacks]
    (let [b-key-width (/ key-width 2)
          x (+ (* 3 (/ b-key-width 2)) (* b key-width))
          y 0
          width b-key-width
          height (* 2 (/ key-height 3))]
      (.fillRect g x y width height))))

(def keyboard-component
  (proxy [JComponent] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (keyboard-paint g))))

(defn get-keyboard-component []
  (.setPreferredSize keyboard-component (Dimension. component-width component-height))
  (.setBackground keyboard-component Color/WHITE)
  keyboard-component)
