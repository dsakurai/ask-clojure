(ns askpath.ask
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            )
  (:import (com.networknt.schema JsonSchemaFactory SpecVersion$VersionFlag JsonSchema ValidatorTypeCode)
           (com.fasterxml.jackson.databind ObjectMapper))
)

(defn -main []
  (let [mapper (ObjectMapper.)
        schema-stream (io/input-stream "config-schema.json")
        json-stream (io/input-stream "config.json")
        factory (JsonSchemaFactory/getInstance SpecVersion$VersionFlag/V7)
        schema (.getSchema factory schema-stream)
        node (.readTree mapper json-stream)
        errors (.validate schema node)]
    (if (.isEmpty errors)
      (println "✅ Valid JSON")
      (do
        (println "❌ Invalid JSON:")
        (doseq [err errors]
          (println (.getMessage err)))))))

