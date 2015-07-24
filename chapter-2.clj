(ns clojure-pathashale.chapter2
  (:require [clojure.string :as str])
  (:import (java.io.File)))

(defrecord Book [title author])

(def b (->Book "Malegalli Madhumagalu" "Kuvempu"))

;; Greeting a user
(defn greeting
  "Returns a greeting of the form: 'Hello user'."
  [user]
  (str "hello " user))

;; Generic greeting which can work without any arguments
(defn generic-greeting
  "Returns a greeting of the form: 'Hello user'."
  ([]
   "hello world")
  ([user]
   (str "hello " user)))

;; Going on a Date accompanied by chaperones
(defn date [person-1 person-2 & chaperones]
  (println person-1 "and" person-2
           "went our with" (count chaperones) "chaperones."))


;; Returns true if a word is indexable (words longer than 2 characters)
(defn indexable-word?
  "Returns true if a word is indexable or not"
  [word]
  (> (count word) 2))

;; Generate index of all words in a sentence,
(require '[clojure.string :as str])
(defn index
  "Returns a index of words given a sentence"
  [sentence]
  (filter indexable-word? (str/split sentence #"\W+")))

; Anonymous function usign fn
(require '[clojure.string :as str])
(defn index
  "Returns a index of words given a sentence"
  [sentence]
  (filter (fn [word] (> (count word) 2)) (str/split sentence #"\W+")))

; Anonymous function usign #()
(require '[clojure.string :as str])
(defn index
  "Returns a index of words given a sentence"
  [sentence]
  (filter #(> (count %) 2) (str/split sentence #"\W+")))

; Local named function
(require '[clojure.string :as str])
(defn index
  "Returns a index of words given a sentence"
  [sentence]
  (let [indexable-word? #(> (count %) 2)]
  (filter indexable-word? (str/split sentence #"\W+"))))

;; Returning a anonymous function
(defn make-greeter
  [greeting-prefix]
  (fn [user] (str greeting-prefix ", " user)))

;; Bindings. Corners of a square given bottom, left and size.
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))

;; Destructuring - Collection
(defn greet-author-2 [{fname :first-name}]
  (println "Hello," fname))

;; Destructuring - Vector
(let [[x y] [1 2 3]]
  [x y])

;; Destructuring - Vector, skipping some elements
;; idomatically _ is used to indicate that we are not interested in that binding
(let [[_ _ x] [1 2 3]]
  [x])

;; Destructuring - Vector, bind to both vector and elements inside it
(let [[x y :as coords] [1 2 3 4 5 6]]
  (str "x: " x ", y: " y ", total dimensions " (count coords)))

;; Ellipsise
(require '[clojure.string :as str])
(defn ellipsise [words]
  "Takes a string and returns first three words followed by ..."
  (let [[one two three] (str/split words #"\s+")]
    (str/join " " [one two three "..."])))

;; If else
(defn small? [number]
  (if (< number 100) "yes" "no"))

;; If else with side effects
(defn small-with-side-effects? [number]
  (if (< number 100)
    "yes"
    (do
      (println "Saw a small number" number)
      "no")))

;; loop and recur
(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

;; recur to start of the function
(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

;; Using sequence library to countdown
(into [] (take 5 (iterate dec 5)))
(into [] (drop-last (reverse (range 6))))
(vec (reverse (rest (range 6))))

;; Index of Any
(defn indexed-collection [coll]
  (map-indexed vector coll))
(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed-collection coll) :when (pred elt)] idx)))
(defn index-of-any [pred coll]
  (first (index-filter pred coll)))
