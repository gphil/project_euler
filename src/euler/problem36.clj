(ns euler.problem36
  (:require [clojure.contrib.math :as m]))

;; The decimal number, 585 = 10010010012 (binary), is palindromic in
;; both bases.

;; Find the sum of all numbers, less than one million, which are
;; palindromic in base 10 and base 2.

;; (No leading zeros!)

(defn split-to-digits [num]
  (map #(Integer/parseInt (str %)) (str num)))

(defn palindromic? [num]
  (= (split-to-digits num)
     (reverse (split-to-digits num))))

(defn bin2dec [num]
  (reduce #(+ (* 2 %1) %2) (split-to-digits num)))

(defn dec2bin [num]
  (apply str
	 (loop [n num l ()]
	   (if (not (= 1 n))
	     (recur (quot n 2) (conj l (rem n 2)))
	     (conj l 1)))))

(defn problem36 []
  (apply +
	 (filter #(and
		   (palindromic? %)
		   (palindromic? (dec2bin %)))
		 (range 1 1000001))))