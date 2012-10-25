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
import httl.util.UrlUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;


/**
 * UrlLoader. (SPI, Singleton, ThreadSafe)
 * 
 * @see httl.Engine#setLoader(Loader)
 * 
 * @author Liang Fei (liangfei0201 AT gmail DOT com)
 */
public class UrlLoader extends AbstractLoader {
    
    public List<String> doList(String directory, String[] suffixes) throws IOException {
        return UrlUtils.listUrl(new URL(directory), getSuffixes());
    }
    
    protected Resource doLoad(String name, String encoding, String path) throws IOException {
        return new UrlResource(this, name, encoding, path);
    }
    
}
