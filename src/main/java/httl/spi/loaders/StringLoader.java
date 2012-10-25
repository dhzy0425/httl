/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package httl.spi.loaders;

import httl.Resource;
import httl.spi.Loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * StringLoader. (SPI, Singleton, ThreadSafe)
 * 
 * @see httl.Engine#setLoader(Loader)
 * @see httl.Engine#addTemplate(String, String)
 * 
 * @author Liang Fei (liangfei0201 AT gmail DOT com)
 */
public class StringLoader extends AbstractLoader {
    
    private final Map<String, String> templates = new ConcurrentHashMap<String, String>();
    
    public boolean has(String name) {
        return templates.containsKey(name);
    }
    
    public void add(String name, String source) {
        templates.put(name, source);
    }

    public void remove(String name) {
        templates.remove(name);
    }

    public void clear() {
        templates.clear();
    }

    public List<String> list() throws IOException {
        return new ArrayList<String>(templates.keySet());
    }

    protected List<String> doList(String directory, String[] suffixes) throws IOException {
        return new ArrayList<String>(templates.keySet());
    }
    
    protected Resource doLoad(String name, String encoding, String path) throws IOException {
        String source = templates.get(path);
        if (source == null) {
            throw new FileNotFoundException("Not found template " + name);
        }
        return new StringResource(this, name, encoding, source);
    }
    
}
