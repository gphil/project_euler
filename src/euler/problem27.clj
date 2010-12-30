(ns euler.problem27
  (:use [clojure.contrib.lazy-seqs :only (primes)])
  (:use [clojure.contrib.math :only (expt)]))

(defn primes-until [num]
  (take-while #(<= % num) primes))

(defn prime? [candidate]
  (contains? (set (primes-until candidate)) candidate))

;; wow this really blows "prime?" away in terms of performance:
(defn faster-prime? [candidate]
  (and
   (> candidate 0)
   (not-any?
    #(zero? (rem candidate %))
    (take-while #(<= (* % %) candidate) primes))))
  
(defn quadratic-value [n a b]
  (+ (expt n 2) (* a n) b))

(defn list-primes [a b]
  (for [n (iterate inc 0) :while (faster-prime? (quadratic-value n a b))]
    (quadratic-value n a b)))

(defn lazy-counts []
  (for [a (range -1000 1001)
	b (primes-until 1000)
	:when (> (+ a b) 0)]
	{:a a :b b :count (count (list-primes a b))}))

(defn problem27 []
  (reduce
   #(if (>= (:count %1) (:count %2)) %1 %2)
   (lazy-counts)))







