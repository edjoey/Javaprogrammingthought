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

public class SyncDemo_OCR_营业执照 {

    private SyncApiClient_OCR_营业执照 syncClient = null;

    public SyncDemo_OCR_营业执照() {
        this.syncClient = SyncApiClient_OCR_营业执照.newBuilder()
                .appKey("25055943")
                .appSecret("22da5b0fa2bd070acddb347a9d1725ea")
                .build();
    }

    public void OCR_营业执照识别Demo(byte[] body) {
        ApiResponse response = syncClient.OCR_营业执照识别(body);
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

