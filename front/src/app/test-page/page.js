'use client'
import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import { Stack } from '@mui/material'
import { useEffect, useState } from 'react'

const TestPage = () => {
  const [temp, settemp] = useState('')
  const [temp2, settemp2] = useState('')

  useEffect(() => {
    console.log(temp)
  }, [temp])
  useEffect(() => {
    console.log(temp2)
  }, [temp2])

  return (
    <Stack>
      <AddressL1 setAddressLevel1={settemp} />
      <AddressL2 addressLevel1={temp} onChange={settemp2} />
    </Stack>
  )
}

export default TestPage
