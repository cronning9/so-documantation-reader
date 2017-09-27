(ns so-docs-reader.core
  (:require [clojure.data.json :as json])
  (:gen-class))

(def docs-root "resources/docs/")

(defn convert-json
  "Converts a json string into a sequence of maps"
  [json-str]
  (lazy-seq (json/read-str json-str
                 :key-fn keyword)))

(defn convert-json-file
  "Convenience function to convert a json file into a sequence of maps"
  [filename]
  (convert-json (slurp (str docs-root filename))))

(defmacro create-data-form
  [symbol filename]
  `(def ~symbol (convert-json-file ~filename)))

(create-data-form cont-del-reasons "contributordeletionreasons.json")
(create-data-form doctags "doctags.json")


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
