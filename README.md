# EIP-PlantUML

EIP-PlantUML provides [Enterprise Integrations Patterns](https://www.enterpriseintegrationpatterns.com/) elements to [PlantUML](http://plantuml.com/) to provide easy support of designing EIP architectures for both, up-front design as well as development-time automated documentation generation.

## Important Information

Please bear in mind this repository is currently *Work in Progress* - therefore new features might be added regularly and breaking changes might be introduced more often than not.


## Getting Started

You will need to download the `EIP-PlantUML.puml` file from the dist folder.
This files includes everything you need to use the EIP patterns in your PlantUML diagramms.

At the top of your PlantUML model you need to include the `EIP-PlantUML.puml` file.

```c#
!include ../EIP-PlantUML/EIP-PlantUML.puml
```

If you want to include the most recent version of the `EIP-PlantUML.puml` file, you can alternatively use

```c#
!includeurl https://raw.githubusercontent.com/plantuml-stdlib/EIP-PlantUML/main/dist/EIP-PlantUML.puml
```

to link to the latest version available in the master repository.

After including the `EIP-PlantUML.puml` file you can start using the EIP patterns as shown below.

```c#
MsgChannel(channel1, "Channel 1")
MsgChannel(channel2, "Channel 2")
Message(msg, "Message")

Send(channel1, msg)
Send(msg, channel2)
```
![Message Example](images/message_example.png)

## Supported Pattern
The following pattern are currently supported:

###

| Category             | Pattern                    | Macro                                            | Image                                       | Image URL |
| --------             | -------                    | -----                                            | -----                                       | --------- |
| Message Construction | Message                    | `Message(alias [,label])`                        | ![](/sprites/message.png)                   |           |
| Message Construction | Command Message            | `CommandMessage(alias [,label])`                 | ![](/sprites/command_message.png)           |           |
| Message Construction | Document Message           | `DocumentMessage(alias [,label])`                | ![](/sprites/document_message.png)          |           |
| Message Construction | Event Message              | `EventMessage(alias [,label])`                   | ![](/sprites/event_message.png)             |           |
| Message Construction | Request-Reply              | `RequestReply(alias [,label])`                   | ![](/sprites/request_reply.png)             |           |
| Message Construction | Return Address             | `ReturnAddress(alias [,label])`                  | ![](/sprites/return_address.png)            |           |
| Message Construction | Correlation Identifer      | `CorrelationIdentifier(alias [,label])`          | ![](/sprites/correlation_identifier.png)    |           |
| Message Construction | Message Sequence           | `MessageSequence(alias [,label])`                | ![](/sprites/message_sequence.png)          |           |
| Message Construction | Message Expiration         | `MessageExpiration(alias [,label])`              | ![](/sprites/message_expiration.png)        |           |
| Message Construction | Format Indicator           | NA                                               |                                             |           |
| Message Routing      | Pipes and Filters          | `Pipe(from, to)`                                 | connector                                   |           |
| Message Routing      | Pipes and Filters          | `Filter(alias [,label])`                         | rectangle                                   |           |
| Message Routing      | Message Router             | `MessageRouter(alias [,label])`                  | ![](/sprites/message_router.png)            |           |
| Message Routing      | Message Filter             | `MessageFilter(alias [,label])`                  | ![](/sprites/message_filter.png)            |           |
| Message Routing      | Dynamic Router             | `DynamicRouter(alias [,label], dynamicrulebase)` | rectangle                                   |           |
| Message Routing      | Recipient List             | `RecipientList(alias [,label])`                  | ![](/sprites/recipient_list.png)            |           |
| Message Routing      | Splitter                   | `Splitter(alias [,label])`                       | ![](/sprites/splitter.png)                  |           |
| Message Routing      | Aggregator                 | `Aggregator(alias [,label])`                     | ![](/sprites/aggregator.png)                |           |
| Message Routing      | Resequencer                | `Resequencer(alias [,label])`                    | ![](/sprites/resequencer.png)               |           |
| Message Routing      | Composed Message Processor | `ComposedMessageProcessor(alias [,label])`       | rectangle                                   |           |
| Message Routing      | Scatter-Gather             | TBD                                              |                                             |           |
| Message Routing      | Routing Slip               | `RoutingSlip(alias [,label])`                    | ![](/sprites/routing_slip.png)              |           |
| Message Routing      | Process Manager            | `ProcessManager(alias [,label])`                 | ![](/sprites/process_manager.png)           |           |
| Message Routing      | Message Broker             | `MessageBroker(alias [,label])`                  | rectangle                                   |           |

### Message Transformation

| Category            | Pattern                                | Macro                                       | Image                                | Image URL |
| --------            | -------                                | -----                                       | -----                                | --------- |
| Message Transformaton  | Message Translator                    | `MessageTranslator(alias [,label])`       | ![](/sprites/message_translator.png) |           |
| Message Transformation | Envelope Wrapper                      | `Wrapper(alias [,label])`                 | ![](/sprites/wrapper.png)            |           |
| Message Transformation | Envelope Wrapper                      | `Unwrapper(alias [,label])`               | ![](/sprites/wrapper.png)            |           |
| Message Transformation | Data Enricher (also Content Enricher) | `DataEnricher(alias [,label], datasource)`| ![](/sprites/data_enricher.png)      |           |
| Message Transformation | Content Filter                        | `ContentFilter(alias [,label])`           | ![](/sprites/content_filter.png)     |           |
| Message Transformation | Claim Check                           | `Item(alias) <<$claim_check>>`, `Item(alias, "<$claim_check>")`, `Item(alias, "label <$claim_check>")` |                                      |           |
| Message Transformation | Normalizer                            | `Normalizer(alias [,label])`              | rectangle                            |           |
| Message Transformation | Canonical Data Model                  | TBD

### Messaging Endpoints

| Category           | Pattern                               | Macro                                   | Image                                    | Image URL |
| --------           | -------                               | -----                                   | -----                                    | --------- |
| Messaging Endpoint | Message Endpoint                      | `MessageEndpoint(alias [,label])`       | ![](/sprites/message_endpoint.png)       |           |
| Messaging Endpoint | Messaging Gateway                     | `MessagingGateway(alias [,label])`      | ![](/sprites/messaging_gateway.png)      |           |
| Messaging Endpoint | Messaging Mapper                      | `MessagingMapper(alias [,label])`       | ![](/sprites/messaging_mapper.png)       |           |
| Messaging Endpoint | Transactional Client                  | `TransactionalProducer(alias [,label])` | ![](/sprites/transactional_producer.png) |           |
| Messaging Endpoint | Transactional Client                  | `TransactionalConsumer(alias [,label])` | ![](/sprites/transactional_consumer.png) |           |
| Messaging Endpoint | Polling Consumer                      | `PollingConsumer(alias [,label])`       | ![](/sprites/polling_consumer.png)       |           |
| Messaging Endpoint | Event-Driven Consumer                 | `EventDrivenConsumer(alias [,label])`   | ![](/sprites/eventdriven_consumer.png)   |           |
| Messaging Endpoint | Competing Consumers                   | TBD                                     |                                          |           |
| Messaging Endpoint | Message Dispatcher                    | `MessageDispatcher(alias [,label])`     | ![](/sprites/message_dispatcher.png)     |           |
| Messaging Endpoint | Selective Consumer (Message Selector) | `SelectiveConsumer(alias [,label])`     | ![](/sprites/selective_consumer.png)     |           |
| Messaging Endpoint | Durable Subscription                  | `DurableSubscriber(alias [,label])`     | ![](/sprites/durable_subscriber.png)     |           |
| Messaging Endpoint | Durable Subscription                  | `NonDurableSubscriber(alias [,label])`  | ![](/sprites/nondurable_subscriber.png)  |           |
| Messaging Endpoint | Idempotent Receiver                   | TBD                                     |                                          |           |
| Messaging Endpoint | Service Activator                     | `ServiceActivator(alias [,label])`      | ![](/sprites/service_activator.png)      |           |

### Messaging Channels

| Category           | Pattern                   | Macro                                 | Image                                     | Image URL |
| --------           | -------                   | -----                                 | -----                                     | --------- |
| Messaging Channels | Messaging Channel         | `MsgChannel(alias [,label])`          | ![](/sprites/queue.png)                   | |
| Messaging Channels | Point-to-Point Channel    | `P2PChannel(alias [,label] )`         | ![](/sprites/queue.png)                   | |
| Messaging Channels | Publish-Subscribe Channel | `PubSubChannel(alias [,label])`       | ![](/sprites/queue.png)                   | |
| Messaging Channels | Datatype Channel          | `DatatypeChannel(alias [,label])`     | ![](/sprites/datatype_channel.png)        | |
| Messaging Channels | Invalid Message Channel   | `InvalidMsgChannel(alias [,label])`   | ![](/sprites/invalid_message_channel.png) | |
| Messaging Channels | Dead Letter Channel       | `DeadLetterChannel(alias [,label])`   | ![](/sprites/dead_letter_queue.png)       | |
| Messaging Channels | Quaranteed Deliver        | TBD                                   |                                           | |
| Messaging Channels | Channel Adapter           | `ChannelAdapterLeft(alias [,label])`  | ![](/sprites/channel_adapter_left.png)    | |
| Messaging Channels | Channel Adapter           | `ChannelAdapterRight(alias [,label])` | ![](/sprites/channel_adapter_right.png)   | |
| Messaging Channels | Messaging Bridge          |`MsgBridge(alias [,label])`            | ![](/sprites/bridge.png)                  | |
| Messaging Channels | Message Bus               | `MsgBus(alias [,label])`              | ![](/sprites/message_bus.png)             | |

### System Management

| Category          | Pattern          | Macro                           | Image                             | Image URL |
| --------          | -------          | -----                           | -----                             | --------- |
| System Management | Control Bus      | `ControlBus(alias)`             | ![](/sprites/control_bus.png)     | |
| System Management |  Detour          | `Detour(alias [,label])`        | ![](/sprites/detour.png)          | |
| System Management |  Wire Tap        | `WireTap(alias [,label])`       | ![](/sprites/wire_tap.png)        | |
| System Management |  Message History | TBD                             | | |
| System Management |  Message Store   | `MessageStore(alias [,label])`  | ![](/sprites/message_store.png)   | |
| System Management |  Smart Proxy     |  `SmartProxy(alias [,label])`   | ![](/sprites/smart_proxy.png)     | |
| System Management |  Test Message    | `TestMessage(alias [,label])`   | ![](/sprites/test_message.png)    | |
| System Management |  Channel Purger  | `ChannelPurger(alias [,label])` | ![](/sprites/channel_purger.png)  | |

## Examples

### Dynamic Message Router
The dynamic message router is one of the more complex patterns. To apply this stencil, you must define the dynamic rule base to be used for this router.

The following example

```csharp
rectangle "Dynamic Rulebase" as rulebase
DynamicRouter(dynamicrouter, "My Dynamic Router", rulebase)

Message(msg, "My Message")
Send(msg, dynamicrouter)

MsgChannel(queue1, "My Destination 1")
Send(dynamicrouter, queue1)

MsgChannel(queue2, "My Destination 2")
Send(dynamicrouter, queue2)
```
results in the graphic below:

![Dynamic Message Router Example](images/dynamicrouter_example.png)

### Claim Check

The Claim Check pattern can be applied to other patterns. There are several ways, this can done using the EIP-PlantUML as shown by the following example. It can be either used as alternative sterotype, as iconic label or as a label combined with a label text.

```csharp
ContentFilter(filter1) <<$claim_check>>

ContentFilter(filter2, "<$claim_check>")

ContentFilter(filter3, "secure filter <$claim_check>")
```

![Claim Check Example](images/claim_check.png)

### Messaging Mapper

The Messaging Mapper transforms domain objects into messages required by the messaging mapper and vice versa.

Therefore, EAI-PLantUML introduces the following metapher for the Messaging Mapper.

![Messaging Mapper Metapher](images/messaging_mapper.png)

The original example introduced by G. Hoppe then can be written like
```csharp
rectangle "Business Object" as obj
component [Messaging\nInfrastructure] as infra
MessagingMapper(mapper, "Messaging Mapper")
obj -- mapper
Send(mapper, infra)
```

![Messaging Mapper Example](images/messaging_mapper_example.png)

### Control Bus

A Control Bus is used to manage an enterprise integration system. It uses the same messaging mechanism used by the application data, but uses separate channels to transmit data that is relevant to the management of components involved in the message flow.

The following example shows how to create the ![original Control Bus example](https://www.enterpriseintegrationpatterns.com/patterns/messaging/ControlBus.html)  easily with PlantUML while connecting each system using the `<-->` command.

```csharp
rectangle System1
rectangle System2
rectangle System3

Message(source)
Message(target)

ControlBus("cb")

Send(source, System1)
Send(System1, System2)
Send(System2, System3)
Send(System3, target)

System1 <--> cb
System2 <--> cb
System3 <--> cb
```

![Contol Bus Example](images/control_bus_example.png)

## Additional Features

## Stereotypes
While stereotypes are great for new users and small examples, documentation frequently used often does not need this additional metadata. Therefore, stereotypes can be swithed off for the overall diagramm using `HIDE_STEREOTYPES()`.

The following example shows an example with hidden sterotypes.

```csharp
HIDE_STEREOTYPES()

Message(src, "source")
MsgChannel(queue, "queue")
Message(dest, "destination")
Send(src, queue)
Send(queue, dest)
````
![Diagram without stereotypes ](images/hide_stereotypes.png)

## Horizontal Layout
In some cases it is required to use a horizontal layout.
You can force this layout by calling

```csharp
LAYOUT_LEFT_RIGHT()
```

## Tests

Tests and scripts are located in the `test`folder of this project.

### Test Script: testlabel.sh

In issue #29 there was an issue reported where `_labels` have been not rendered. This ussue was reported on Windows 10 using PLantUML v1.2019.8. To avoid this, in the definition string, the label declaration has to be precendet by a space. This test script verifies this requirement. The script return `0`if the test is passend, `1`otherwise.

```bash
 FAILED: Issues detected
420:!define ChannelAdapterLeft(_alias, _label) rectangle "<$channel_adapter_left>\r_label" as _alias <<channel adapter left>>
422:!define ChannelAdapterRight(_alias, _label) rectangle "<$channel_adapter_right>\r_label" as _alias <<channel adapter right>>
AndreassMacBook:test andreas$ ./testlabel.sh
 PASSED: No issues detected.
 ```

## Background
Enterprise Intergartion Patterns are a set of 65 patterns, mainly based on messaging concepts introduced Gregor Hohpe and Bobby Woolf. The Website [Enterprise Integration Patterns](https://www.enterpriseintegrationpatterns.com/) provides an extensive overview of these patterns.

These patterns come in handy when dealing with large scale enterprise architectures especially based on messaging systems.

While documenting large systems with manual tools like Microsoft Visio takes an enourmous amount of time, [PlantUML](http://plantuml.com/) provides an elegant way of coding such architecures top-down or creating documentation based on your source codes.

There fore the EIP patterns is designed to support this bottom up documentation. The EIP patterns also play well with the [C4-PlantUML](https://github.com/RicardoNiepel/C4-PlantUML) extension by Ricardo Niepel when it comes to Level 4 of the C4 model.

The [C4 Model](http://c4model.com/) is a elegant way introduced by Simon Brow for describing and communicating software architectures up-front.

## Feedback

[@aheil](https://twitter.com/aheil)\
[#eipplantuml](https://twitter.com/hashtag/eipplantuml?src=hash)

## Contribution

If interested in contributing to this project, please make sure to read the [Contribution Guidelines](https://github.com/aheil/EIP-PlantUML/blob/master/CONTRIBUTING.md) first.

## Further References
* [AWS-PlantUML](https://github.com/milo-minderbinder/AWS-PlantUML)
* [Office Icons for PlantUML](https://github.com/Roemer/plantuml-office)
* [Azure-PlantUML](https://github.com/RicardoNiepel/Azure-PlantUML)
