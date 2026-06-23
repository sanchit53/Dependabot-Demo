#include <stdio.h>
#include <curl/curl.h>

int main(void) {
  printf("{\"service\":\"c\",\"status\":\"ok\",\"libcurl\":\"%s\"}\n", curl_version());
  return 0;
}
