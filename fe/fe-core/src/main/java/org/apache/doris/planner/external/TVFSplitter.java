// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.planner.external;

import org.apache.doris.analysis.Expr;
import org.apache.doris.common.UserException;
import org.apache.doris.planner.Split;
import org.apache.doris.planner.Splitter;
import org.apache.doris.tablefunction.ExternalFileTableValuedFunction;
import org.apache.doris.thrift.TBrokerFileStatus;

import com.google.common.collect.Lists;
import org.apache.hadoop.fs.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TVFSplitter implements Splitter {

    private static final Logger LOG = LogManager.getLogger(TVFSplitter.class);

    private ExternalFileTableValuedFunction tableValuedFunction;

    public TVFSplitter(ExternalFileTableValuedFunction tableValuedFunction) {
        this.tableValuedFunction = tableValuedFunction;
    }

    @Override
    public List<Split> getSplits(List<Expr> exprs) throws UserException {
        List<Split> splits = Lists.newArrayList();
        List<TBrokerFileStatus> fileStatuses = tableValuedFunction.getFileStatuses();
        for (TBrokerFileStatus fileStatus : fileStatuses) {
            Path path = new Path(fileStatus.getPath());
            Split split = new FileSplit(path, 0, fileStatus.getSize(), new String[0]);
            splits.add(split);
        }
        return splits;
    }

}