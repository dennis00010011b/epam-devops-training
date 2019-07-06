[xml]$XmlDoc = Get-Content VMs.xml
foreach ($VM in $XmlDoc.VMs.VM){
remove-vm $VM.Name -force
if (-Not($VM.isImport)){
remove-item "$(Join-Path $VM.VHDPath $VM.Name).vhdx"
}
}
