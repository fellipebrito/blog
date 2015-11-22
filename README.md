# Sample webapp

## Routing
Compojure apps will run on any ring-compatible server, but we recommend [http-kit](http://www.http-kit.org/) for its performance and [massive concurrency](http://www.http-kit.org/600k-concurrent-connection-http-kit.html).

## Middleware
Clojure uses [Ring](https://github.com/ring-clojure/ring) for routing. For more information read [Ring Spec](https://github.com/mmcgrana/ring/blob/master/SPEC)

## Templates
The project is using [Selmer](https://github.com/yogthos/Selmer#usage). A fast, Django inspired template system in Clojure.
For Markdown, it is using this [parser](https://github.com/yogthos/markdown-clj).

## Usage
```
lein run -m site.core
```
