(ns euler.problem22 
  (:require [clojure.contrib.string :as s]))

(defn namestring []
  (slurp "/Users/gphil/Code/workspace/project_euler/src/names.txt"))

(defn ordered-names []
  (sort (re-seq #"\w+" (namestring))))

(def *letters* "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn letter-scores []
  (apply hash-map (interleave *letters* (iterate inc 1))))

(defn name-sum [namestr]
  (reduce + (map (letter-scores) namestr)))

(defn name-scores []
  (map name-sum (ordered-names)))

(defn problem22 []
  (reduce + (map * (name-scores) (iterate inc 1))))

(time (problem22))

  ;; answer should be 871198282
