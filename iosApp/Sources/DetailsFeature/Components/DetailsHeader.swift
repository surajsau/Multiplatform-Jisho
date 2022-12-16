//
//  DetailsHeader.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared

struct DetailsHeader: View {

    let model: DetailsViewModel.Header

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(self.model.onyomi)
                    .font(.title3)

                Text(self.model.kanji)
                    .font(.largeTitle)
                    .fontWeight(.bold)
            }

            Spacer()
        }
    }
}

struct PreviewDetailsHeader: PreviewProvider {

    static var previews: some View {
        DetailsHeader(model: DetailsViewModel.Header(kanji: "怒る", onyomi: "おこる"))
    }
}
