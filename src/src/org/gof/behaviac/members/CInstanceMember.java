package org.gof.behaviac.members;

import java.util.List;

import org.gof.behaviac.Agent;
import org.gof.behaviac.ClassInfo;
import org.gof.behaviac.Debug;
import org.gof.behaviac.EOperatorType;
import org.gof.behaviac.OperationUtils;
import org.gof.behaviac.utils.Utils;

public class CInstanceMember implements IInstanceMember {
	protected String _instance = "Self";
	protected IInstanceMember _indexMember = null;
	protected ClassInfo _clazz;

	public CInstanceMember(ClassInfo clazz) {
		_clazz = clazz;
		_indexMember = null;
	}

	public CInstanceMember(ClassInfo clazz, String instance, IInstanceMember indexMember) {
		_instance = instance;
		_indexMember = indexMember;
		_clazz = clazz;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int GetCount(Agent self) {
		List list = (List) this.GetValueObject(self);
		if (list != null) {
			return list.size();
		}

		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void SetValue(Agent self, IInstanceMember right, int index) {
		Object rightObject = right.GetValueObject(self);
		Debug.Check(rightObject instanceof List);
		List il = (List) rightObject;
		Object item = il.get(index);
		this.SetValue(self, item);
	}

	@Override
	public Object GetValueObject(Agent self) {
		Debug.Check(false);
		return null;
	}

	@Override
	public void SetValue(Agent self, Object value) {
		Debug.Check(false);
	}

	@Override
	public void SetValue(Agent self, IInstanceMember right) {
		SetValue(self, right.GetValueObject(self));
	}

	@Override
	public void SetValueAs(Agent self, IInstanceMember right) {
		Object v = Utils.ConvertFromObject(_clazz.getElemClass(), _clazz.isList(), right.GetValueObject(self));
		SetValue(self, v);
	}

	@Override
	public boolean Compare(Agent self, IInstanceMember right, EOperatorType comparisonType) {
		 Object leftValue = this.GetValueObject(self);
		 Object rightValue = right.GetValueObject(self);

         return OperationUtils.Compare(leftValue, rightValue, comparisonType);
	}

	@Override
	public void Compute(Agent self, IInstanceMember right1, IInstanceMember right2, EOperatorType computeType) {
		Object rightValue1 = right1.GetValueObject(self);
		Object rightValue2 = right2.GetValueObject(self);

        SetValue(self, OperationUtils.Compute(rightValue1, rightValue2, computeType));
	}

	@Override
	public void Run(Agent self) {
	}

}
