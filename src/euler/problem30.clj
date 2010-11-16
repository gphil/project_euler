(ns euler.problem30
  (:require [clojure.contrib.math :as m]))

;; Find the sum of all the numbers that can be written as the sum of
;; fifth powers of their digits.

(defn split-to-digits [n]
  (let [num-digits (.length (str n))]
    (for [i (range num-digits)]
      (rem (quot n (m/expt 10 i)) 10))))

(defn sum-digits-to-fifth [n]
  (apply + (map #(m/expt % 5) (split-to-digits n))))

(defn eq-sum-digits-to-fifth? [n]
  (= n (sum-digits-to-fifth n)))

;; lazy seq with upper bound = 6*9^5 because 7*9^5 is only 6 digits,
;; so it cannot be a 7 digit number
(defn problem30 []
     (apply +
	    (filter #(eq-sum-digits-to-fifth? %)
		    (take-while #(<= % 354294) (iterate inc 2)))))
