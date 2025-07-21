(ns askpath.ask
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            )
  (:import (com.networknt.schema JsonSchemaFactory SpecVersion$VersionFlag JsonSchema ValidatorTypeCode)
           (com.fasterxml.jackson.databind ObjectMapper))
  )

(defn validate-schema [schema-stream json-stream]
  (let [mapper (ObjectMapper.)
        factory (JsonSchemaFactory/getInstance SpecVersion$VersionFlag/V7)
        schema (.getSchema factory schema-stream)
        node (.readTree mapper json-stream)
        errors (.validate schema node)
        ]
    {:errors errors :node node}
  ))

(defn -main []
  (let [snippets-json-str (slurp "snippets.json")
        result (validate-schema
                  (slurp "snippets-schema.json") ;; Might be slower than (io/input-stream "filename")
                  snippets-json-str
                 )
        errors (:errors result)
        snippets-map (json/parse-string snippets-json-str)
        ]
    (if (.isEmpty errors)
      (println (get snippets-map "entries"))
      (do
        (println "Invalid JSON:")
        (doseq [err errors]
          (println (.getMessage err))))))
  )

