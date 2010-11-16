(ns euler.problem48
  (:require [clojure.contrib.math :as m]))

;; The series, 1^1 + 2^2 + 3^3 + ... + 1010 = 10405071317.

;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.

;; wow, this was really fast to brute force
(defn problem48 []
  (mod (apply + (for [i (range 1 1001)] (m/expt i i))) 10000000000))
