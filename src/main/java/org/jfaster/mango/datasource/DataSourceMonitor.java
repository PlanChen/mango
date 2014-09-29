/*
 * Copyright 2014 mango.jfaster.org
 *
 * The Mango Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.jfaster.mango.datasource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 监控datasource
 *
 * @author ash
 */
public class DataSourceMonitor {

    private static ConcurrentHashMap<DataSource, Object> map = new ConcurrentHashMap<DataSource, Object>();

    private static volatile boolean forceCheckAutoCommit = false;

    public static boolean needCheckAutoCommit(DataSource ds) {
        return forceCheckAutoCommit || map.get(ds) != null;
    }

    public static void resetAutoCommitFail(DataSource ds) {
        map.putIfAbsent(ds, new Object());
    }

    public static void setForceCheckAutoCommit(boolean forceCheckAutoCommit) {
        DataSourceMonitor.forceCheckAutoCommit = forceCheckAutoCommit;
    }
}