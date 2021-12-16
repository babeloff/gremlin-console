package edu.vanderbilt.mesolab.symcps

import groovy.console.ui.Console
import io.github.classgraph.ClassGraph


// from gremlin-driver module
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection
// from gremlin-core module
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal


/**
 * https://github.com/apache/groovy
 */
class GremlinConsole {

    static main(args) {
        Console console = new Console();

        def g = traversal().withRemote(
                DriverRemoteConnection.using('localhost', 8182))

        console.setVariable("g", g)

        console.setVariable("src_name", 'foo');
        console.setVariable("src_uuid", '20bf8270-5dc8-11ec-bf63-0242ac130002');

        console.setVariable("tgt_name", 'bar');
        console.setVariable("src_uuid", 'cc9950cd-776c-4c43-b98f-e0df92d7c33c');


        List<URI> classpath = new ClassGraph().getClasspathURIs()
        classpath.forEach { uri ->
            console.shell.getClassLoader().addURL(uri.toURL())
        }

        console.run();
    }

}