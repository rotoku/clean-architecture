resource "local_file" "pets" {
  content  = "This is a list of pets:\n- Dog\n- Cat\n- Fish"
  filename = "${path.module}/pets.json"
}