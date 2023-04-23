def buildApp{
    echo "building application"
}
def testApp{
    echo "testing application"
}
def deployApp{
    echo "deployinh application"
    echo "deploying version ${params.VERSION}"
}

return