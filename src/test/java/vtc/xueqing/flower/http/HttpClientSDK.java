package vtc.xueqing.flower.http;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientSDK {
    // 使用自定义线程池（推荐）
    private static final ExecutorService asyncExecutor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    /**
     * 异步GET请求（返回原始HttpResponse）
     */
    public CompletableFuture<HttpResponse> getAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return HttpUtil.createGet(url).execute();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, asyncExecutor);
    }

    /**
     * 异步GET请求（只返回body字符串）
     */
    public CompletableFuture<String> getAsyncAsString(String url) {
        return getAsync(url).thenApply(HttpResponse::body);
    }

    /**
     * 异步GET请求（自动JSON解析）
     */
    public <T> CompletableFuture<T> getAsyncAsJson(String url, Class<T> beanClass) {
        return getAsync(url).thenApply(response ->
                JSONUtil.toBean(response.body(), beanClass)
        );
    }

    // 可以添加更多方法：postAsync, putAsync等
    /**
     * 基础异步POST请求（返回HttpResponse）
     * @param url 请求地址
     * @param body 请求体内容
     * @return CompletableFuture<HttpResponse>
     */
    public CompletableFuture<HttpResponse> postAsync(String url, String body) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return HttpUtil.createPost(url)
                        .body(body)
                        .execute();
            } catch (Exception e) {
                throw new CompletionException("POST请求失败", e);
            }
        }, asyncExecutor);
    }

    // 关闭线程池的方法
    public void shutdown() {
        asyncExecutor.shutdown();
    }
}