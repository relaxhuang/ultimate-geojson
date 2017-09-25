package com.erumi.ugeojson.builder.geometry;

import java.util.List;

import com.erumi.ugeojson.builder.common.BuilderConstants;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.MultiPointDto;

/**
 * @author moksuzer
 *
 */
public class MultiPointBuilder extends GeometryBuilder<MultiPointDto> {

	private static final MultiPointBuilder INSTANCE = new MultiPointBuilder();

	public static MultiPointBuilder getInstance() {
		return INSTANCE;
	}

	private MultiPointBuilder() {
	}

	@Override
	public String toGeometryGeoJSON(MultiPointDto multiPoint) {
		if (multiPoint == null || multiPoint.getPositions() == null || multiPoint.getPositions().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.MultiPoint);
		builder.append(BuilderConstants.COORDINATES_SPACE);

		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		List<PositionDto> positions = multiPoint.getPositions();
		for (int i = 0; i < positions.size(); i++) {
			PositionDto position = positions.get(i);
			builder.append(PositionBuilder.getInstance().position(position));
			if (i < positions.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);
		endBuilder(builder);
		return builder.toString();
	}

}
