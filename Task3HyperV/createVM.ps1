$VMName = $args[0]
$ISO = $args[1]
$Memory = $args[2]
$VHDPath = $args[3]
 $VM = @{
     Name = $VMName
     MemoryStartupBytes = $Memory
     Generation = 2
     NewVHDPath = "$VHDPath$VMName.vhdx"
     NewVHDSizeBytes = 53687091200
         
 }
 echo Creating $VMName $ISO $Memory $VHDPath
 New-VM @VM

 Add-VMDvdDrive -VMName $VMName -Path $ISO

 $firmw = Get-VMFirmvare $VMName
 Set-VMFirmvare -VMName $VMName -BootOrder $firmw.BootOrder[2]




