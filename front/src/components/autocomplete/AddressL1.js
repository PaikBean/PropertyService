import { fetchAddressesLevel1, setAddressL1 } from '@/store/slices/addressSlice'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const AddressL1 = ({ value, onChange, sx }) => {
  const dispatch = useDispatch()
  const { addressL1Options, L1status, L1error, addressL1 } = useSelector(
    (state) => state.address
  )

  useEffect(() => {
    if (L1status === 'idle') {
      dispatch(fetchAddressesLevel1())
    }
  }, [L1status, dispatch])

  const handleAddressChange = (event, value) => {
    dispatch(setAddressL1(value ? value.addressLevel1Id : ''))
    onChange(value ? value.addressLevel1Id : '')
  }

  return (
    <Autocomplete
      value={
        addressL1Options
          ? addressL1Options.find(
              (option) => option.addressLevel1Id === value
            ) || null
          : null
      }
      options={addressL1Options || []}
      getOptionLabel={(addressL1Options) =>
        addressL1Options.addressLevel1 || ''
      }
      onChange={handleAddressChange}
      renderInput={(params) => (
        <TextField {...params} label="Address Level 1" />
      )}
      sx={sx}
    />
  )
}

AddressL1.displayName = 'AddressL1'

export default AddressL1
