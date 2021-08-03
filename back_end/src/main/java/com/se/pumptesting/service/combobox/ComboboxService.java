
package com.se.pumptesting.service.combobox;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

public interface ComboboxService {

	ApplicationResponse getComboRecords(RequestWrapper requestWrapper);

	ApplicationResponse getModelMasters();

}
