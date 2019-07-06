[xml]$XmlDoc = Get-Content VMs.xml
foreach ($VM in $XmlDoc.VMs.VM){
if ($VM.isImport) {
Import-VM -Path $VM.SourcePath -VhdDestinationPath $VM.VHDPath -Copy -GenerateNewId
}
  else{
	.\createVM.ps1 $VM.Name $VM.SourcePath $VM.Memory $VM.VHDPath 

}
}
