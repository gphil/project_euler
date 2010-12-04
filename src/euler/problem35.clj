(ns euler.problem35
  (:require [euler.problem36 :as p36])
  (:require [clojure.contrib.lazy-seqs :as l])
  (:require [clojure.contrib.combinatorics :as c])
  (:require [clojure.set :as s])
  (:require [clojure.contrib.math :as m])
  (:require [clojure.contrib.seq-utils :as su]))
  
;; The number, 197, is called a circular prime because all rotations
;; of the digits: 197, 971, and 719, are themselves prime.

;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17,
;; 31, 37, 71, 73, 79, and 97.

;; How many circular primes are there below one million?

(defn primes-until [num]
  (set (take-while #(< % num) l/primes)))

(defn candidates [p]
  (map #(Integer/parseInt %)
       (map #(apply str %)
	    (su/rotations (str p)))))

(def million-primes (primes-until 100000))

(defn prime? [p]
  (contains? million-primes p))

(defn circular-prime? [p]
  (every? prime? (candidates p)))

(defn problem35 []
  (count (filter circular-prime? million-primes)))



