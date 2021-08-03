
package com.se.pumptesting.generic.dao;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

public interface WorkOrderDetailsDao {

	ApplicationResponse getWorkOrderDetailsData(RequestWrapper requestWrapper);

}
