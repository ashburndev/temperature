class MyJob {
  static triggers = {
    simple name: 'mySimpleTrigger', startDelay: 5000, repeatInterval: 1000
  }
  def group = "MyGroup"
  def description = "Example job with Simple Trigger"

  def execute(){
    print "Job run!"
  }
}

