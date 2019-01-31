### post提交数据,raw格式数据获取

```
public String query(@RequestHeader(value = "sign")String sign,
                                    HttpServletRequest request){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
       
        try{
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            //校验
            String checkSign = HmacSha256Util.sha256_HMAC(sb.toString(),"migu123456");
            //sign相等
        }catch (UnsupportedEncodingException e) {
            log.error("Method:crossQuery error.",e);
            searchVO.setCode("2000002");
            searchVO.setMessage("接口调用失败");
        } catch (IOException e) {
            log.error("Method:crossQuery error.",e);
            searchVO.setCode("2000002");
            searchVO.setMessage("接口调用失败");
        }
        stopWatch.stop();
        searchVO.setCostTime(stopWatch.getTotalTimeMillis());
        return "";
    }
```