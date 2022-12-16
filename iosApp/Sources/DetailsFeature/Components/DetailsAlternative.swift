//
//  DetailsAlternatives.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

struct DetailsAlternatives: View {

    let alternative: String

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("Alternatives")
                    .font(.title)
                    .fontWeight(.semibold)
                    .padding(.bottom, 8)

                Text(self.alternative)
                    .font(.body)
            }

            Spacer()
        }
    }
}

struct DetailsAlternativePreview: PreviewProvider {
    static var previews: some View {
        DetailsAlternatives(alternative: "楽しい, たのしい")
    }
}
