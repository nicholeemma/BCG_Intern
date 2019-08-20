import requests
import json
base_URI = 'https://interns.bcgdvsydney.com'

request_get =  requests.get(base_URI+'/api/v1/key')
# Get the returned json object
JSON =  request_get.json()
# Check the content
print(JSON)
# Check the status of request, should show 201
print(request_get.status_code)



key = JSON.get('key')
expires = JSON.get('expires')
print(key)
print(expires)


info = {
	'name' : 'Jiayue Yang',
	'email' : 'jiayueya@andrew.cmu.edu'
}
# Post information to specified URI
request_post = requests.post(base_URI+'/api/v1/submit?apiKey='+key,data=json.dumps(info))
# Check
print(request_post.json())
# Check the status of request, should show 202
print(request_post.status_code)