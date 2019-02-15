/*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;

public class SyncDemo_行政区域查询 {

    private SyncApiClient_行政区域查询 syncClient = null;

    public SyncDemo_行政区域查询() {
        this.syncClient = SyncApiClient_行政区域查询.newBuilder()
                .appKey("your app key here")
                .appSecret("your app secret here")
                .build();
    }

    public void 行政区域查询Demo() {
        ApiResponse response = syncClient.行政区域查询("", "1", "true", "1", "20", "base", null, null, "JSON");
        printResponse(response);
    }

    private static void printResponse(ApiResponse response) {
        try {
            System.out.println("response code = " + response.getStatusCode());
            System.out.println("response content = " + new String(response.getBody(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

