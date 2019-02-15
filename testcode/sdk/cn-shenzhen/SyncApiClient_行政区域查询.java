
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
public final class SyncApiClient_行政区域查询 extends BaseApiClient {
    public final static String GROUP_HOST = "district.market.alicloudapi.com";

    private SyncApiClient_行政区域查询(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<SyncApiClient_行政区域查询.Builder, SyncApiClient_行政区域查询>{

        @Override
        protected SyncApiClient_行政区域查询 build(BuilderParams params) {
            return new SyncApiClient_行政区域查询(params);
        }
    }

    public static Builder newBuilder(){
        return new SyncApiClient_行政区域查询.Builder();
    }

    public static SyncApiClient_行政区域查询 getInstance(){
        return getApiClassInstance(SyncApiClient_行政区域查询.class);
    }

    public ApiResponse 行政区域查询(String keywords, String subdistrict, String showbiz, String page, String offset, String extensions, String filter, String callback, String output) {
        String _apiPath = "/v3/config/district";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);

        _apiRequest.addParam("keywords", keywords, ParamPosition.QUERY, false);
        _apiRequest.addParam("subdistrict", subdistrict, ParamPosition.QUERY, false);
        _apiRequest.addParam("showbiz", showbiz, ParamPosition.QUERY, false);
        _apiRequest.addParam("page", page, ParamPosition.QUERY, false);
        _apiRequest.addParam("offset", offset, ParamPosition.QUERY, false);
        _apiRequest.addParam("extensions", extensions, ParamPosition.QUERY, false);
        _apiRequest.addParam("filter", filter, ParamPosition.QUERY, false);
        _apiRequest.addParam("callback", callback, ParamPosition.QUERY, false);
        _apiRequest.addParam("output", output, ParamPosition.QUERY, false);

        return syncInvoke(_apiRequest);
    }

}

