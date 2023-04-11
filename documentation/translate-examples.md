# Translate Demo Model - Training Material

## How to use this training material?

//Add description of this page and how to use it

## Conditional default

### Conditional default example 1

Conditional mapping using a "default" clause that sets an attribute if it is unmapped.

The attribute EngineSpecification->capacityUnit is mapped to xml path engine->engineType->engineDetail->
volumeCapacityUnit, if that path does not exist, then the attribute is set to "UK Gallon".


- [Schema - add link](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/schemas/conditional-default/example-1.xsd)
- [Rosetta - add link](xxxxx)
- [Mapper Optional - add link](xxxxx)


#### Conditional default example 1.1

- [Example 1.1 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-default/example-1/example-1-1.xml)
- [Example 1.1 - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-default/example-1/example-1-1.json)


#### Conditional default example 1.2

- [Example 1.2 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-default/example-1/example-1-2.xml)
- [JSON output - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-default/example-1/example-1-2.json)


## Conditional set

### Conditional set example 1

Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path.

The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when the value at xml path The attribute EngineSpecification->capacityUnit is conditionally set from
xml path engine->engineType->engineDetail->volumeCapacityUnit when the value at xml path engineType->engineDetail->
powerUnit = "Cylinder".


- [Schema - add link](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/schemas/conditional-set/example-1.xsd)
- [Rosetta - add link](xxxxx)
- [Mapper Optional - add link](xxxxx)


#### Conditional set example 1.1

- [Example 1.1 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-set/example-1/example-1-1.xml)
- [Example 1.1 - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-set/example-1/example-1-1.json)


#### Conditional default example 1.2

- [Example 1.2 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-set/example-1/example-1-2.xml)
- [JSON output - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-set/example-1/example-1-2.json)

### Conditional set example 2

Conditional mapping using "set" with a "when" clause that is predicated on a xml element at the specified xml path
exists.

The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when there is a value at xml path engine->engineType->engineDetail->powerUnit.


- [Schema - add link](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/schemas/conditional-set/example-2.xsd)
- [Rosetta - add link](xxxxx)
- [Mapper Optional - add link](xxxxx)


#### Conditional set example 2.1

- [Example 2.1 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-set/example-2/example-2-1.xml)
- [Example 2.1 - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-set/example-2/example-2-1.json)


#### Conditional default example 2.2

- [Example 2.2 - XML Input](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/cdm-sample-files/conditional-set/example-2/example-2-2.xml)
- [Example 2.2 - JSON Output](https://github.com/rosetta-models/demo/blob/docs-1/rosetta-source/src/main/resources/result-json-files/conditional-set/example-2/example-2-2.json)


