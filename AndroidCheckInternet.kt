class isConnected : Thread() {

  private fun connectedToTheNetwork(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if(activeNetworkInfo != null) true else false
  }
  private fun isNetworkAvailable(): Boolean {
        if (connectedToTheNetwork()) {
            try {
                val urlc = URL("http://clients3.google.com/generate_204")
                        .openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Android")
                urlc.setRequestProperty("Connection", "close")
                urlc.setConnectTimeout(500)
                urlc.connect()
                Log.e(TAG, urlc.toString())
                return if (urlc.getResponseCode() === 204 && urlc.getContentLength() === 0) true else false
            } catch (e: NetworkOnMainThreadException) {
                Log.e(TAG, "Probably no internet", e)
                return false
            }

        } else {
            return false
        }
        return false;
    }
}
