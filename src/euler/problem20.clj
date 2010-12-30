(ns euler.problem20
  (:require [clojure.contrib.math :as m])
  (:require [clojure.contrib.lazy-seqs :as l]))

;; A fraction in lowest terms with a prime denominator other than 2 or
;; 5 (i.e. coprime to 10) always produces a repeating decimal. The
;; period of the repeating decimal of 1/p is equal to the order of 10
;; modulo p. If 10 is a primitive root modulo p, the period is equal
;; to p − 1; if not, the period is a factor of p − 1. This result can
;; be deduced from Fermat's little theorem, which states that 10p−1 =
;; 1 (mod p).

;; the order of a mod p is the smallest k such that a^k is 1 mod p

(defn order [a p]
  (first
   (filter #(not (nil? %))
	   (for [k (range 1 p)]
	     (if (= 1 (mod (m/expt a k) p))
	       k)))))

(defn problem20 []
  (+ 1
     (apply max
	    (filter #(not (nil? %))
		    (map #(order 10 %)
			 (take-while #(< % 1000) l/primes))))))



