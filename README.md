# office-queue-management
Soft Eng 2: Office queue management system - Group P12


## How to start the back-end server
Using intelliJ go in folder `it.polito.ezqueue`, right click on EzqueueApplication and Run it


## Config File

The Yaml config file is by default in the following path:

`server\ezqueue\src\main\java\it\polito\ezqueue\resources\config.yml`

and it can be modified in the Constants interface.

In the file we specify services, desks and bindings between them.
The format of the file must be the following:

```
---
services:
    - service:
        service-id: ID1
        service-description: A very nice service
        service-time: 42.0
    - service:
        service-id: ID2
        service-description: Another nice service
        service-time: 19.0
desks:
    - desk:
        desk-id: 1
        offered-services:
            - ID1
            - ID2
    - desk:
        desk-id: 2
        offered-services:
            - ID1
    - desk:
        desk-id: 3
        offered-services:
            - ID2
...
```
Notice  how a Yaml file starts with `---` and finishes with `...`.
Remember that desk-id must be an integer value.
