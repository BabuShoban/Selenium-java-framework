Feature: sample testng project

@Assignment2
Scenario: To validate login facebook and post the status
Given open facebook application with "FbCredentials" user credentials
When click on status
Then post "Hello World" message

@Assignment2
Scenario: login app with credentials
Given open wallethub application and login with "WalletHubCredentials"
When select review star
And select health insurance and submit review comments
Then verify submitted review comments

