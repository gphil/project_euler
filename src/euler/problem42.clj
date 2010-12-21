(ns euler.problem42
  (:use [clojure.set :only (intersection)]))


(defn words []
  (re-seq #"\w+" (slurp "/Users/gphil/Documents/Code/euler/src/euler/words.txt")))

(defn triangle-numbers [n]
  (for [i (range 1 (+ 1 n))]
    (* (/ i 2) (+ i 1))))

(def *letters* "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn letter-scores []
  (apply hash-map (interleave *letters* (iterate inc 1))))

(defn word-score [word]
  (reduce + (map (letter-scores) word)))

(defn word-scores []
  (map word-score (words)))

(defn problem42 []
  (count (filter #(contains? (set (triangle-numbers 20)) %) (word-scores))))
