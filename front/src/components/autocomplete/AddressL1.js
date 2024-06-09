import { fetchAddressesLevel1, setAddressL1 } from '@/store/slices/addressSlice'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const AddressL1 = ({ onChange }) => {
  const dispatch = useDispatch()
  const { addressL1Options, level1Status, level1Error, addressL1 } =
    useSelector((state) => state.address)

  useEffect(() => {
    if (level1Status === 'idle') {
      dispatch(fetchAddressesLevel1())
    }
  }, [level1Status, dispatch])

  const handleAddressChange = (event, value) => {
    dispatch(setAddressL1(value ? value.addressLevel1Id : ''))
    onChange(value ? value.addressLevel1Id : '')
  }

  return (
    <Autocomplete
      options={addressL1Options || []}
      getOptionLabel={(addressL1Options) =>
        addressL1Options.addressLevel1 || ''
      }
      onChange={handleAddressChange}
      renderInput={(params) => (
        <TextField {...params} label="Address Level 1" />
      )}
    />
  )
}

AddressL1.displayName = 'AddressL1'

export default AddressL1
