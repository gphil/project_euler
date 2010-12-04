(ns euler.problem34
  (:require [euler.problem36 :as p36])
  (:require [clojure.contrib.math :as m]))

;; Find the sum of all numbers which are equal to the sum of the
;; factorial of their digits.

(defn fac [num]
  (loop [n num a 1]
    (if (zero? n)
      a
      (recur (dec n) (* a n)))))

;; is there an upper bound?

;; (apply + (map fac '(9 9 9 9 9 9 9))) yields 2540160, which is
;; greater than 9999999 so at this point you can't catch the sum of
;; the factorial with the number

;; there should be an even lower bound?

(defn eq-to-fac-digits? [num]
  (= num (apply + (map fac (p36/split-to-digits num)))))

(defn problem34 []
  (apply + (filter eq-to-fac-digits? (range 10 999999))))