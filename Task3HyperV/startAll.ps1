[xml]$XmlDoc = Get-Content VMs.xml
foreach ($VM in $XmlDoc.VMs.VM) {
echo $VM.Name starting
Start-VM -Name $VM.Name
}

