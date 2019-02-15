
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

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class SyncApiClient_OCR_营业执照 extends BaseApiClient {
    public final static String GROUP_HOST = "qyocrbl.market.alicloudapi.com";

    private SyncApiClient_OCR_营业执照(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<SyncApiClient_OCR_营业执照.Builder, SyncApiClient_OCR_营业执照>{

        @Override
        protected SyncApiClient_OCR_营业执照 build(BuilderParams params) {
            return new SyncApiClient_OCR_营业执照(params);
        }
    }

    public static Builder newBuilder(){
        return new SyncApiClient_OCR_营业执照.Builder();
    }

    public static SyncApiClient_OCR_营业执照 getInstance(){
        return getApiClassInstance(SyncApiClient_OCR_营业执照.class);
    }

    public ApiResponse OCR_营业执照识别(byte[] _body) {
        String _apiPath = "/clouds/ocr/businessLicense";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_BODY, GROUP_HOST, _apiPath, _body);


        return syncInvoke(_apiRequest);
    }

}

