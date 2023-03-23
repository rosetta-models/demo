Example 18:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path and rosettaPath with multi-cardinality and meta.
The attribute Root->usEngineVersion->capacityUnit and Root->ukEngineVersion->capacityUnit are conditional set from xml
path engineType->volumeCapacityUnit when "engineType->volumeCapacityUnit->scheme" = "usScheme" and "ukScheme" and
rosettaPath is Root->ukEngineVersion->capacityUnit and Root->usEngineVersion->capacityUnit respectively