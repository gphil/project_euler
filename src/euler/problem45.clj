(ns euler.problem45
  (:use clojure.contrib.math))

(defn triangular-numbers []
  (for [n (iterate inc 1)]
    (/ (* n (+ n 1)) 2)))

(def pentagonal-numbers
  (for [n (iterate inc 1)]
    (/ (* n (- (* 3 n)  1)) 2)))

(defn hexagonal-numbers []
  (for [n (iterate inc 1)]
    (* n (- (* 2 n)  1))))

;; bad performing attempt:
(defn tri-penta-hexes []
  (for [n (triangular-numbers)
	:when (and
	       (= (first (drop-while #(< % n) pentagonal-numbers)) n)
	       (= (first (drop-while #(< % n) (hexagonal-numbers))) n))] n))

;; hexagonal numbers are triangular by definition, so we can test them
;; for pentagonality. I got the pentagonality test below from
;; wikipedia, but it can easily be derived using the quadratic formula

(defn pentagonal? [candidate]
  (zero? (rem
	  (/ (+ 1 (Math/sqrt (+ 1 (* 24 candidate)))) 6)
	  1)))

(defn problem45 []
  (take 3 (filter pentagonal? (hexagonal-numbers))))


  